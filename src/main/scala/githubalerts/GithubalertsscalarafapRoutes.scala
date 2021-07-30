package githubalerts

import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import io.circe.generic.auto._
import org.http4s.circe.CirceEntityEncoder._

object GithubalertsscalarafapRoutes {

  def appRoutes[F[_]: Sync](service: SubscriptionService[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "subscription" / user =>
         service.getSubscriptions(user).flatMap(Ok(_))
      case POST -> Root / "subscription" / user =>
         service.getSubscriptions(user).flatMap(Ok(_))
    }
  }
}