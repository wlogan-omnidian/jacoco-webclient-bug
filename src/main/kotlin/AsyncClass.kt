import kotlinx.coroutines.delay
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToMono


class AsyncClass(baseUrl: String) {

  private val webClient = WebClient.builder()
      .baseUrl(baseUrl)
      .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .exchangeStrategies(
          ExchangeStrategies.builder()
              .codecs {
                it.defaultCodecs().maxInMemorySize(-1)
              }
              .build()
      )
      .build()

  suspend fun doWebWork(input: String): String = webClient.post()
      .uri("function")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(input)
      .retrieve()
      .awaitBody()

  suspend fun doWebWork2(input: String): String = webClient.post()
      .uri("function")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(input)
      .retrieve()
      .bodyToMono<String>().awaitSingle()

  suspend fun doLocalWork(input: String): String = input + "foo".also { delay(1L) }
}