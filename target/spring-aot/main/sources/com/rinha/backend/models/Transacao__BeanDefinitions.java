package com.rinha.backend.models;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link Transacao}.
 */
@Generated
public class Transacao__BeanDefinitions {
  /**
   * Get the bean definition for 'transacao'.
   */
  public static BeanDefinition getTransacaoBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Transacao.class);
    beanDefinition.setInstanceSupplier(Transacao::new);
    return beanDefinition;
  }
}
