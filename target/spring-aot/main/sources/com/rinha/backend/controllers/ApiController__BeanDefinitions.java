package com.rinha.backend.controllers;

import com.rinha.backend.services.TransacaoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApiController}.
 */
@Generated
public class ApiController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'apiController'.
   */
  private static BeanInstanceSupplier<ApiController> getApiControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ApiController>forConstructor(TransacaoService.class)
            .withGenerator((registeredBean, args) -> new ApiController(args.get(0)));
  }

  /**
   * Get the bean definition for 'apiController'.
   */
  public static BeanDefinition getApiControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApiController.class);
    beanDefinition.setInstanceSupplier(getApiControllerInstanceSupplier());
    return beanDefinition;
  }
}
