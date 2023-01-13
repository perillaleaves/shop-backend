package perillaleaves.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor interceptor;

    public WebConfig(LoginInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 프로그램에서 제공하는 URL
                .allowedOrigins("http://mullae.com", "http://localhost:3000") // 청을 허용할 출처를 명시, 전체 허용 (가능하다면 목록을 작성한다.)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Access-Control-Allow-Origin",
                        "*",
                        "Access-Control-Allow-Methods",
                        "POST, GET, PUT, DELETE",
                        "Access-Control-Allow-Headers",
                        "Origin, X-Requested-With, Content-Type, Accept",
                        "X-Token")
                .allowCredentials(true) // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다),
                .exposedHeaders("token")
                .maxAge(3000);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor)
//                .addPathPatterns("/**", "/create/item")
//                .excludePathPatterns("/signup", "/overlap/loginid", "/overlap/phonenumber", "/overlap/email",
//                        "/loginid", "/find/loginid", "/find/password", "/update/password", "/login",
//                        "/user/{accessToken}", "/user", "/{color_id}", "/items", "/item/{item_id}",
//                        "/items/stock", "/item/{item_id}/{color_id}", "/cart/{color_id}", "/cart/{accessToken}",
//                        "/{accessToken}/cartitem/{cart_item_id}", "/cart/count");
//
//    }


}
