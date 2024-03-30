package com.rinha.backend.services;

import com.rinha.backend.repositories.TransacaoRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TransacaoService}.
 */
@Generated
public class TransacaoService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'transacaoService'.
   */
  private static BeanInstanceSupplier<TransacaoService> getTransacaoServiceInstanceSupplier() {
    return BeanInstanceSupplier.<TransacaoService>forConstructor(TransacaoRepository.class)
            .withGenerator((registeredBean, args) -> new TransacaoService(args.get(0)));
  }

  /**
   * Get the bean definition for 'transacaoService'.
   */
  public static BeanDefinition getTransacaoServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TransacaoService.class);
    beanDefinition.setInstanceSupplier(getTransacaoServiceInstanceSupplier());
    return beanDefinition;
  }
}
