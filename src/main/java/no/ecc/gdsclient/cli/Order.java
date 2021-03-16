package no.ecc.gdsclient.cli;

import no.ecc.gdsclient.utility.UrlPrefix;
import no.ecc.gdsclient.ws.client.OrderClient;
import no.ecc.gdsclient.ws.impl.OrderProductRequest;
import no.ecc.gdsclient.ws.impl.OrderRequest;

public class Order {

    public static void main(String[] args) throws Exception {
        String urlPrefix = System.getProperty(UrlPrefix.URL_PREFIX_KEY);
        if (urlPrefix == null) {
            urlPrefix = System.getenv(UrlPrefix.URL_PREFIX_KEY);
        }
        if (urlPrefix == null) {
            System.err.println("missing property " + UrlPrefix.URL_PREFIX_KEY
                    + " set to something like https://username:password@qaprimar.ecc.no/qaprimar");
            System.exit(-1);
        }

        OrderClient oc = new OrderClient();
        oc.setUrlPrefix(urlPrefix);

        if (args.length > 1) {
            String command = args[0];
            if (args.length == 4 && command.equals("placeOrder")) {
                String userId = args[1];
                String[] productIds = args[2].split(",");
                int subscriptionTypeId = Integer.parseInt(args[3]);

                OrderRequest order = new OrderRequest();
                order.setUserId(userId);

                OrderProductRequest[] orderProducts = new OrderProductRequest[productIds.length];
                for (int i = 0; i < productIds.length; i++) {
                    OrderProductRequest orderProduct = new OrderProductRequest();
                    orderProduct.setProductId(productIds[i]);
                    orderProduct.setQuantity(1);
                    orderProduct.setSubscriptionTypeId(subscriptionTypeId);
                    orderProducts[i] = orderProduct;
                }
                order.setOrderProducts(orderProducts);

                Integer orderId = oc.placeOrder(order);
                System.out.println("placeOrder returned orderId=" + orderId);
                return;
            } else if (args.length == 2 && command.equals("activateOrder")) {
                int orderId = Integer.parseInt(args[1]);
                Boolean r = oc.activateOrder(orderId, true);
                System.out.println("activateOrder returned " + r);
                return;
            }
        }

        System.err.println("Usage: ..Order [placeOrder userId PRODUCTA,PRODUCTB subscriptionTypeId|activateOrder orderId] "
                + UrlPrefix.URL_PREFIX_KEY
                + " set to something like https://username:password@qaprimar.ecc.no/qaprimar");
        System.exit(-1);
    }

}
