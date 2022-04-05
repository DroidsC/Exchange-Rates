# Cizmar Cristian Exchange

You can install the app by opening the project in Android Studio or by installing the 'app.apk'
located in the root directory.

Some mentions about the free version of the API:

- https://exchangeratesapi.io/pricing/
- it only allows 250 requests. If there are not requests left you can change the API_KEY
- it only supports "EUR" as the base currency, it doesn't allow other currencies
- it doesn't allow getting the exchange rate values from multiple days (for the history screen)
  using a single request, so we unfortunately have to make multiple requests
- it doesn't support HTTPS, only HTTP