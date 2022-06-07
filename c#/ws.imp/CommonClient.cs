using System;
using System.ServiceModel;

namespace gdsclientc.ws.imp
{
	public class CommonClient
	{
		String url;
		BasicHttpsBinding binding;

		public CommonClient(String serviceBasicUrl)
		{
			if (binding == null) {
				binding = (BasicHttpsBinding) GetBinding();
			}
			this.url = serviceBasicUrl;
		}

		public BasicHttpsBinding GetBindingForEndpoint() {
			return binding;
		}

		public EndpointAddress GetEndpointAddress(String service) {
			return GetEndpoint(url + service);
		}

		private EndpointAddress GetEndpoint(string endpointUrl)
		{
			if (!endpointUrl.StartsWith("https://"))
			{
				throw new UriFormatException("The endpoint URL must start with https://.");
			}
			return new System.ServiceModel.EndpointAddress(endpointUrl);
		}


		private static System.ServiceModel.Channels.Binding GetBinding()
		{
			System.ServiceModel.BasicHttpsBinding binding = new System.ServiceModel.BasicHttpsBinding();
			if (binding == null)
				return null;
			binding.Security.Mode = System.ServiceModel.BasicHttpsSecurityMode.Transport;
			binding.Security.Transport.ClientCredentialType = System.ServiceModel.HttpClientCredentialType.Basic;
			binding.Security.Transport.ProxyCredentialType = System.ServiceModel.HttpProxyCredentialType.None;
			binding.Security.Message.ClientCredentialType = System.ServiceModel.BasicHttpMessageCredentialType.UserName;
			binding.MaxReceivedMessageSize = 2147483647;
			return binding;
		}
	}
}

