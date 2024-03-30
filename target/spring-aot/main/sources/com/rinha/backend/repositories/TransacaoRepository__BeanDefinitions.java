package com.rinha.backend.repositories;

import javax.sql.DataSource;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TransacaoRepository}.
 */
@Generated
public class TransacaoRepository__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'transacaoRepository'.
   */
  private static BeanInstanceSupplier<TransacaoRepository> getTransacaoRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<TransacaoRepository>forConstructor(DataSource.class)
            .withGenerator((registeredBean, args) -> new TransacaoRepository(args.get(0)));
  }

  /**
   * Get the bean definition for 'transacaoRepository'.
   */
  public static BeanDefinition getTransacaoRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TransacaoRepository.class);
    beanDefinition.setInstanceSupplier(getTransacaoRepositoryInstanceSupplier());
    return beanDefinition;
  }
}
