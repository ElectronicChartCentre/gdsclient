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
			var httpsBinding = new BasicHttpsBinding();
			httpsBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.Basic;
			httpsBinding.Security.Mode = BasicHttpsSecurityMode.Transport;

			var integerMaxValue = int.MaxValue;
			httpsBinding.MaxBufferSize = integerMaxValue;
			httpsBinding.MaxReceivedMessageSize = integerMaxValue;
			httpsBinding.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
			httpsBinding.AllowCookies = true;

			return httpsBinding;
		}
	}
}

