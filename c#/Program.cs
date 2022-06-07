using gdsclientc.ws.imp;
using NamespaceName;

//Need to be specified...
String username = "";
String password = "";
String serviceBasicUrl = "";
int distId = -1;
int custId = -1;
int vesselId = -1;
String userId = "";

CommonClient commonClient = new CommonClient(serviceBasicUrl);

System.ServiceModel.BasicHttpsBinding binding = (System.ServiceModel.BasicHttpsBinding)commonClient.GetBindingForEndpoint();
System.ServiceModel.EndpointAddress remoteDistributorAddress = commonClient.GetEndpointAddress("DistributorServiceV2");

using (DistributorServiceV2Client serviceClient = new DistributorServiceV2Client(binding, remoteDistributorAddress))
{
    serviceClient.ClientCredentials.UserName.UserName = username;
    serviceClient.ClientCredentials.UserName.Password = password;
    Distributor[] ds = serviceClient.getDistributors();
    distId = (int)ds[0].distributorId;

    DistributorInfoV2 info = serviceClient.getDistributorInfo(distId);
    Console.WriteLine(info.name + " " + info.email);

    Country[] countries = serviceClient.getAllCountries();
    Country norway = null;
    foreach (Country c in countries)
    {
        if (c.name.Equals("Norway"))
        {
            norway = c;
        }
    }

    VesselTonnage[] vesselTonnages = serviceClient.getAllVesselTonnages();
    VesselCategory[] vesselCategories = serviceClient.getAllVesselCategories();

    IdName[] customerIds = serviceClient.getCustomerIdNames(distId);
    int customerId = -1;
    if (customerIds.Length > 0)
    {
        customerId = customerIds[0].id;
    }

    //New customer
    try
    {
        CustomerInfo newCustomer = new CustomerInfo();
        newCustomer.email = "someone@somewhere.no";
        newCustomer.name = "New B2B Test";
        newCustomer.phone = "00000000";
        newCustomer.countryId = norway.code;
        newCustomer.distributorId = (int)info.distributorId;
        customerId = serviceClient.saveCustomerInfoAndReturnId(newCustomer);
        Console.WriteLine("Customer ID: " + customerId);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }

    //Existing customer
    CustomerInfo customerInfo = serviceClient.getCustomerInfo(customerId);
    customerInfo.contact = "someone";
    bool cOk = serviceClient.saveCustomerInfo(customerInfo);
    custId = customerId;
    Console.WriteLine("Customer saved: " + cOk);


    //New vessel
    try
    {
        VesselInfo newVesselInfo = new VesselInfo();
        newVesselInfo.name = "test B2B";
        newVesselInfo.customerId = customerId;
        newVesselInfo.distributorId = (int)info.distributorId;
        newVesselInfo.callSign = "KAN";
        newVesselInfo.categoryId = vesselCategories[0].categoryId;
        newVesselInfo.tonnageId = vesselTonnages[0].code;
        newVesselInfo.flagId = norway.code;
        vesselId = serviceClient.saveVesselInfoAndReturnId(newVesselInfo);
        Console.WriteLine("Vessel ID: " + vesselId);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
        //Already created this - so pick up first of vessels for customer.
        Vessel[] vessels = serviceClient.getVessels(custId);
        vesselId = (int)vessels[0].vesselId;
    }
    //Existing vessel
    VesselInfo vesselInfo = serviceClient.getVesselInfo(vesselId);
    Console.WriteLine(vesselInfo.name + ", is enabled: " + vesselInfo.enabled + ", " + vesselInfo.customerId);
    customerInfo = serviceClient.getCustomerInfo((int)vesselInfo.customerId);
    Console.WriteLine(customerInfo.customerId + " is enabled: " + customerInfo.enabled);
    vesselInfo.name = "Test B2B";
    bool vOk = serviceClient.saveVesselInfo(vesselInfo);
    Console.WriteLine("Vessel saved: " + vOk);
    userId = vesselInfo.userId;

}

//Product IDs need to be found in catalog file. Ref Product Catalogue in service documentation
//Example values is put in here
System.ServiceModel.EndpointAddress remoteOrderAddress = commonClient.GetEndpointAddress("OrderServiceV2");
using (OrderServiceV2Client orderClient = new OrderServiceV2Client(binding, remoteOrderAddress))
{
    orderClient.ClientCredentials.UserName.UserName = username;
    orderClient.ClientCredentials.UserName.Password = password;
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.userId = userId;
    OrderProductRequest p1 = new OrderProductRequest();
    p1.productId = "NO4M0613";
    p1.quantity = 1;
    p1.subscriptionTypeId = 3;
    OrderProductRequest p2 = new OrderProductRequest();
    p2.productId = "NO5F0613";
    p2.quantity = 1;
    p2.subscriptionTypeId = 3;
    OrderProductRequest[] productRequests = { p1, p2 };


    orderRequest.orderProducts = productRequests;
    int orderId = orderClient.placeOrder(orderRequest);
    Console.WriteLine("Order id: " + orderId);
    bool oOk = orderClient.activateOrder(orderId);
    Console.WriteLine("Order activated: " + oOk);

    OrderProductRequest p3 = new OrderProductRequest();
    p3.productId = "102CA0024100N04700W";
    p3.quantity = 1;
    p3.subscriptionTypeId = 3;
    OrderProductRequest[] s102ProductRequests = { p3 };
    OrderRequest s102orderRequest = new OrderRequest();
    s102orderRequest.userId = userId;
    s102orderRequest.orderProducts = s102ProductRequests;
    int s102orderId = orderClient.placeOrder(s102orderRequest);
    Console.WriteLine("Order id: " + orderId);
    oOk = orderClient.activateOrder(s102orderId);
    Console.WriteLine("Order activated: " + oOk);
}

System.ServiceModel.EndpointAddress remoteDownloadAddress = commonClient.GetEndpointAddress("CellDownloadServiceV2");
using (CellDownloadServiceV2Client downloadClient = new CellDownloadServiceV2Client(binding, remoteDownloadAddress))
{
    downloadClient.ClientCredentials.UserName.UserName = username;
    downloadClient.ClientCredentials.UserName.Password = password;
    DateTime lastYear = DateTime.Now.AddYears(-1);
    bool ok = downloadClient.downloadForVessel(vesselId, true, lastYear, true);
    int i = 0;
    while (true)
    {
        FileDownloadWrapper f = downloadClient.get(i++);
        if (f == null)
        {
            break;
        }
        Console.WriteLine(f.path);
        //break to not potentially download thousands of exchange nodes
        if (i > 5) break;
    }
    Console.WriteLine(ok);
}


