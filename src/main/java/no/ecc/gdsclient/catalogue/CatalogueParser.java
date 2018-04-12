package no.ecc.gdsclient.catalogue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.apache.commons.text.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;

import no.ecc.gdsclient.utility.IOUtils;
import no.ecc.gdsclient.utility.UrlPrefix;

/**
 * Read and parse a catalogue.xml/.ctl from a GDS to obtain information on
 * cells, products++.
 */
public class CatalogueParser implements Serializable {

    private static final long serialVersionUID = 2L;

    private final Map<String, Cell> cellById = new HashMap<>();
    private final Multimap<String, Cell> cellsByCountryCode = HashMultimap.create();
    private final Map<String, Product> productById = new HashMap<>();
    private final Map<Integer, Country> countryById = new HashMap<>();
    private final Multimap<String, Country> countriesByCode = HashMultimap.create();
    private final Map<Integer, SubscriptionType> subscriptionTypeById = new HashMap<>();
    private final List<ProductReplace> productReplaces = new ArrayList<>();
    private final Map<Integer, SaleReportGroup> saleReportGroupById = new HashMap<>();

    private Integer version;
    private Date createdDate;
    private Date fetchedDate;

    private CatalogueParser() {

    }

    public static CatalogueParser create(UrlPrefix urlPrefix) throws IOException, DocumentException {
        URL url = new URL(urlPrefix.getUrlPrefix() + "/catalogue.ctl?version=3");
        return create(url.openStream());
    }

    public static CatalogueParser create(String urlPrefix) throws IOException, DocumentException {
        return create(new UrlPrefix(new URL(urlPrefix)));
    }

    public static CatalogueParser create(byte[] catalogue) throws IOException, DocumentException {
        return create(new ByteArrayInputStream(catalogue));
    }

    @SuppressWarnings("unchecked")
    static CatalogueParser create(InputStream in) throws IOException, DocumentException {
        ZipInputStream zinstream = null;
        try {
            zinstream = new ZipInputStream(in);
            zinstream.getNextEntry();
            SAXReader saxReader = new SAXReader();
            Document catalogue = saxReader.read(zinstream);

            CatalogueParser p = new CatalogueParser();
            p.fetchedDate = new Date();

            Element catalogueNode = (Element) catalogue.selectSingleNode("/catalogue");
            if (catalogueNode != null) {
                String date = catalogueNode.attributeValue("date");
                if (date != null) {
                    try {
                        SimpleDateFormat catalogueDateFormat = new SimpleDateFormat("yyyyMMdd HH':'mm");
                        p.createdDate = catalogueDateFormat.parse(date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                String version = catalogueNode.attributeValue("version");
                if (version != null) {
                    p.version = Integer.valueOf(version);
                }
            }

            List<Element> saleReportGroupNodes = catalogue.selectNodes("/catalogue/saleReportGroups/saleReportGroup");
            for (Element node : saleReportGroupNodes) {
                SaleReportGroup srg = SaleReportGroup.parse(node);
                p.saleReportGroupById.put(srg.getId(), srg);
            }

            List<Element> subscriptionTypeNodes = catalogue
                    .selectNodes("/catalogue/subscriptionTypes/subscriptionType");
            for (Element subscriptionTypeNode : subscriptionTypeNodes) {
                SubscriptionType subscriptionType = SubscriptionType.parse(subscriptionTypeNode);
                p.subscriptionTypeById.put(subscriptionType.getId(), subscriptionType);
            }

            List<Element> countryNodes = catalogue.selectNodes("/catalogue/countries/country");
            for (Element countryNode : countryNodes) {
                Country country = Country.parse(p, countryNode);
                country.saleReportGroup = p.saleReportGroupById.get(country.saleReportGroupId);
                p.countryById.put(country.getId(), country);
                p.countriesByCode.put(country.getCode(), country);
            }

            List<Element> cellNodes = catalogue.selectNodes("/catalogue/enc/cell");
            for (Element cellNode : cellNodes) {
                Cell cell = Cell.parse(p, cellNode);
                p.cellById.put(cell.getCellId(), cell);
                p.cellsByCountryCode.put(cell.getCountry().getCode(), cell);
            }

            List<Element> productTypeNodes = catalogue.selectNodes("/catalogue/producttypes/producttype");
            for (Element productTypeNode : productTypeNodes) {
                ProductType pt = ProductType.parse(productTypeNode);
                List<Element> productNodes = productTypeNode.selectNodes("products/product");
                for (Element productNode : productNodes) {
                    Product product = Product.parse(p, productNode);
                    product.productType = pt;
                    p.productById.put(product.getProductId(), product);
                }
            }

            List<Element> productReplaceNodes = catalogue.selectNodes("/catalogue/productReplaces/productReplace");
            for (Element productReplaceNode : productReplaceNodes) {
                ProductReplace pr = ProductReplace.parse(p, productReplaceNode);
                p.productReplaces.add(pr);
            }

            return p;
        } finally {
            IOUtils.close(zinstream);
            IOUtils.close(in);
        }
    }

    public Integer getVersion() {
        return version;
    }

    public Date getCreatedDate() {
        return new Date(createdDate.getTime());
    }

    public Date getFetchedDate() {
        return new Date(fetchedDate.getTime());
    }

    public Cell getCell(String cellId) {
        return cellById.get(cellId);
    }

    public String getTitleForCell(String cellId) {
        Cell cell = getCell(cellId);
        return cell == null ? null : cell.getTitle();
    }

    public Collection<Cell> getCells() {
        return Collections.unmodifiableCollection(cellById.values());
    }

    public Collection<Cell> getCellsForCountryCode(String countryCode) {
        return Collections.unmodifiableCollection(cellsByCountryCode.get(countryCode));
    }

    public boolean hasCountryWithCode(String countryCode) {
        return countriesByCode.containsKey(countryCode);
    }

    public int getCountryCodeCount() {
        return countriesByCode.size();
    }

    public Product getProduct(String productId) {
        return productById.get(productId);
    }

    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(productById.values());
    }

    public Country getCountry(Integer countryId) {
        return countryById.get(countryId);
    }

    public Collection<Country> getCountries() {
        return Collections.unmodifiableCollection(countryById.values());
    }

    public SubscriptionType getSubscriptionType(Integer id) {
        return subscriptionTypeById.get(id);
    }

    public Collection<SubscriptionType> getSubscriptionTypes() {
        return Collections.unmodifiableCollection(subscriptionTypeById.values());
    }

    public Collection<ProductReplace> getProductReplaces() {
        return Collections.unmodifiableList(productReplaces);
    }

    public ProductReplace getProductReplace(String originalProductId, String replaceProductId) {
        for (ProductReplace pr : productReplaces) {
            if (pr.getOriginalProductId().equals(originalProductId)
                    && pr.getReplaceProductId().equals(replaceProductId)) {
                return pr;
            }
        }
        return null;
    }

    public static final class Cell implements Serializable {

        private static final long serialVersionUID = 1L;

        private String cellId;
        private String title;
        private Integer cscl;
        private Integer edtn;
        private Integer updn;
        private Integer reissue = Integer.valueOf(0);
        private Country country;
        private String coverageWkt;
        private final Envelope envelope = new Envelope();

        private final List<Product> products = new ArrayList<Product>();

        private static Cell parse(CatalogueParser parser, Element cellNode) {
            Cell cell = new Cell();

            double nlat = Double.NaN;
            double slat = Double.NaN;
            double elon = Double.NaN;
            double wlon = Double.NaN;

            for (int i = 0, size = cellNode.nodeCount(); i < size; i++) {
                Node node = cellNode.node(i);
                if (node instanceof Element) {
                    Element cellSubEl = (Element) node;
                    String n = cellSubEl.getName();
                    String value = cellSubEl.getText();

                    if ("dsnm".equals(n)) {
                        cell.cellId = value;
                    } else if ("cell_title".equals(n)) {
                        cell.title = StringEscapeUtils.unescapeXml(value);
                    } else if ("cscl".equals(n)) {
                        cell.cscl = Integer.valueOf(value);
                    } else if ("countryId".equals(n)) {
                        Integer countryId = Integer.valueOf(value);
                        cell.country = parser.getCountry(countryId);
                    } else if ("coverage".equals(n)) {
                        cell.coverageWkt = value;
                    } else if ("nlat".equals(n)) {
                        nlat = Double.valueOf(value);
                    } else if ("slat".equals(n)) {
                        slat = Double.valueOf(value);
                    } else if ("wlon".equals(n)) {
                        wlon = Double.valueOf(value);
                    } else if ("elon".equals(n)) {
                        elon = Double.valueOf(value);
                    } else if ("edtn".equals(n)) {
                        cell.edtn = Integer.valueOf(value);
                    } else if ("last_updn".equals(n)) {
                        cell.updn = Integer.valueOf(value);
                    } else if ("last_reissue".equals(n)) {
                        cell.reissue = Integer.valueOf(value);
                    }
                }
            }

            if (!Double.isNaN(nlat) && !Double.isNaN(slat) && !Double.isNaN(wlon) && !Double.isNaN(elon)) {
                cell.envelope.expandToInclude(wlon, slat);
                cell.envelope.expandToInclude(elon, nlat);
            }

            return cell;
        }

        private Cell() {

        }

        public String getCellId() {
            return cellId;
        }

        public String getTitle() {
            return title;
        }

        public Integer getCscl() {
            return cscl;
        }

        public Integer getEdtn() {
            return edtn;
        }

        public Integer getUpdn() {
            return updn;
        }

        public Country getCountry() {
            return country;
        }

        public Geometry getCoverage() throws com.vividsolutions.jts.io.ParseException {
            WKTReader reader = new WKTReader();
            return reader.read(coverageWkt);
        }

        public Envelope getEnvelope() {
            return new Envelope(envelope);
        }

        private void addProduct(Product p) {
            products.add(p);
        }

        @Override
        public int hashCode() {
            return getCellId().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;

            }
            if (!(obj instanceof Cell)) {
                return false;
            }
            Cell o = (Cell) obj;
            return getCellId().equals(o.getCellId());
        }

        public Integer getReissue() {
            return reissue;
        }

        public String getIdString() {
            return cellId + "," + edtn + "," + updn + "," + getReissue();
        }

        @Override
        public String toString() {
            return getIdString();
        }

    }

    public static final class Product implements Serializable {

        private static final long serialVersionUID = 1L;

        private String productId;
        private String title;
        private Country country;
        private Boolean allowMultiPermit;
        private Integer priceBand;
        private ProductType productType;

        private final Set<Cell> cells = new HashSet<Cell>();

        private static Product parse(CatalogueParser parser, Element productElement) {
            Product p = new Product();

            p.title = StringEscapeUtils.unescapeXml(productElement.attributeValue("title"));
            p.productId = productElement.attributeValue("id");
            Integer countryId = Integer.valueOf(productElement.attributeValue("countryId"));
            p.country = parser.getCountry(countryId);
            p.allowMultiPermit = Boolean.valueOf(productElement.attributeValue("allowMultiPermit"));
            p.priceBand = Integer.valueOf(productElement.attributeValue("priceBand"));

            for (int i2 = 0, size = productElement.nodeCount(); i2 < size; i2++) {
                Node cellsNode = productElement.node(i2);
                if (cellsNode instanceof Element) {
                    Element cellsElement = (Element) cellsNode;
                    for (int i3 = 0, size3 = cellsElement.nodeCount(); i3 < size3; i3++) {
                        Node cellNode = cellsElement.node(i3);
                        if ((cellNode instanceof Element) && cellNode.getName().equals("cell_in_product")) {
                            Element cellElement = (Element) cellNode;
                            String cellId = cellElement.attributeValue("id");
                            Cell cell = parser.getCell(cellId);
                            if (cell != null) {
                                p.addCell(cell);
                                cell.addProduct(p);
                            }
                        }
                    }
                }
            }

            return p;
        }

        private Product() {

        }

        public String getProductId() {
            return productId;
        }

        public String getTitle() {
            return title;
        }

        public Country getCountry() {
            return country;
        }

        public Boolean isAllowMultiPermit() {
            return allowMultiPermit;
        }

        public Integer getPriceBand() {
            return priceBand;
        }

        private void addCell(Cell c) {
            cells.add(c);
        }

        public Set<Cell> getCells() {
            return Collections.unmodifiableSet(cells);
        }

        public ProductType getProductType() {
            return productType;
        }
    }

    public static final class Country implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;
        private String code;
        private String name;
        private Boolean allowCreditNote;
        private Boolean realTimeEnc;
        private String realTimeEncProductId;
        private Integer saleReportGroupId;
        private SaleReportGroup saleReportGroup;
        private final List<SubscriptionType> subsriptionTypes = new ArrayList<CatalogueParser.SubscriptionType>();

        @SuppressWarnings("rawtypes")
        private static Country parse(CatalogueParser parser, Element countryElement) {
            Country c = new Country();

            c.id = Integer.valueOf(countryElement.attributeValue("id"));
            c.code = countryElement.attributeValue("code");
            c.name = StringEscapeUtils.unescapeXml(countryElement.attributeValue("name"));
            c.allowCreditNote = Boolean.valueOf(countryElement.attributeValue("allowCredit"));
            c.realTimeEnc = Boolean.valueOf(countryElement.attributeValue("realTimeEnc"));
            c.realTimeEncProductId = countryElement.attributeValue("realTimeEncProductId");

            String saleReportGroupId = countryElement.attributeValue("saleReportGroupId");
            if (saleReportGroupId != null) {
                c.saleReportGroupId = Integer.valueOf(saleReportGroupId);
            }

            List subscriptionTypeNodes = countryElement
                    .selectNodes("possibleSubscriptionTypes/possibleSubscriptionType");
            for (Iterator it = subscriptionTypeNodes.iterator(); it.hasNext();) {
                Element e = (Element) it.next();
                Integer id = Integer.valueOf(e.attributeValue("id"));
                SubscriptionType st = parser.getSubscriptionType(id);
                c.subsriptionTypes.add(st);
            }

            return c;
        }

        public Integer getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Boolean getAllowCreditNote() {
            return allowCreditNote;
        }

        public boolean isRealTimeEnc() {
            return Boolean.TRUE.equals(realTimeEnc);
        }

        public String getRealTimeEncProductId() {
            return realTimeEncProductId;
        }

        public SaleReportGroup getSaleReportGroup() {
            return saleReportGroup;
        }

        public List<SubscriptionType> getSubscriptionTypes() {
            return Collections.unmodifiableList(subsriptionTypes);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Country)) {
                return false;
            }
            return ((Country) obj).getId().equals(getId());
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

    }

    public static final class SubscriptionType implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;
        private String name;
        private Integer periode;
        private Boolean commonPermitEnd;
        private String role;
        private Boolean realTimeEnc;
        private Boolean autoRenewal;
        private Boolean educational;

        private static SubscriptionType parse(Element stElement) {
            SubscriptionType st = new SubscriptionType();

            st.id = Integer.valueOf(stElement.attributeValue("id"));
            st.name = StringEscapeUtils.unescapeXml(stElement.attributeValue("name"));
            st.periode = Integer.valueOf(stElement.attributeValue("periode"));
            st.commonPermitEnd = Boolean.valueOf(stElement.attributeValue("commonPermitEnd"));
            st.role = stElement.attributeValue("role");
            st.realTimeEnc = Boolean.valueOf(stElement.attributeValue("realTimeEnc"));
            st.autoRenewal = Boolean.valueOf(stElement.attributeValue("autoRenewal"));
            st.educational = Boolean.valueOf(stElement.attributeValue("educational"));

            return st;
        }

        private SubscriptionType() {

        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getPeriode() {
            return periode;
        }

        public Boolean getCommonPermitEnd() {
            return commonPermitEnd;
        }

        public String getRole() {
            return role;
        }

        public Boolean getRealTimeEnc() {
            return realTimeEnc;
        }

        public Boolean getAutoRenewal() {
            return autoRenewal;
        }

        public Boolean getEducational() {
            return educational;
        }

    }

    public static final class ProductType implements Serializable {

        private static final long serialVersionUID = 1L;

        private static ProductType parse(Element productTypeNode) {
            ProductType pt = new ProductType();
            pt.description = StringEscapeUtils.unescapeXml(productTypeNode.attributeValue("description"));
            pt.id = Integer.valueOf(productTypeNode.attributeValue("id"));
            return pt;
        }

        private ProductType() {

        }

        private Integer id;
        private String description;

        public Integer getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    public static final class ProductReplace implements Serializable {

        private static final long serialVersionUID = 1L;

        private String originalProductId;

        private String replaceProductId;

        private Date date;

        public static ProductReplace parse(CatalogueParser parser, Element productReplacesElement) {
            ProductReplace pr = new ProductReplace();

            for (int i = 0, size = productReplacesElement.nodeCount(); i < size; i++) {
                Node productReplaceNode = productReplacesElement.node(i);
                if (productReplaceNode instanceof Element) {
                    Element productReplace = (Element) productReplaceNode;
                    String n = productReplace.getName();
                    String value = productReplace.getText();

                    if ("original".equals(n)) {
                        pr.originalProductId = value;
                    } else if ("replace".equals(n)) {
                        pr.replaceProductId = value;
                    } else if ("time".equals(n)) {
                        pr.date = new Date(Long.valueOf(value));
                    }
                }
            }

            return pr;
        }

        public String getOriginalProductId() {
            return originalProductId;
        }

        public String getReplaceProductId() {
            return replaceProductId;
        }

        public Date getDate() {
            return date;
        }

        private ProductReplace() {

        }

        @Override
        public String toString() {
            return getOriginalProductId() + "->" + getReplaceProductId() + "@" + getDate();
        }

    }

    public static final class SaleReportGroup implements Serializable {

        private static final long serialVersionUID = 1L;

        private static SaleReportGroup parse(Element node) {
            Integer id = Integer.valueOf(node.attributeValue("id"));
            String name = StringEscapeUtils.unescapeXml(node.attributeValue("name"));
            return new SaleReportGroup(id, name);
        }

        private final Integer id;
        private final String name;

        private SaleReportGroup(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

}
