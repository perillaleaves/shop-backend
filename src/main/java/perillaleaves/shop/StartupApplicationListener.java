package perillaleaves.shop;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import perillaleaves.shop.service.GenerateTestService;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    GenerateTestService generateTestService;

    public StartupApplicationListener(GenerateTestService generateTestService) {
        this.generateTestService = generateTestService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateTestService.generate();
    }
}