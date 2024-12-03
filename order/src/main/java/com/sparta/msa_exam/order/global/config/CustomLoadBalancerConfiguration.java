package com.sparta.msa_exam.order.global.config;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@LoadBalancerClient(name = "product-service")
public class CustomLoadBalancerConfiguration {

	// @Bean
	// public ServiceInstanceListSupplier weightedServiceInstanceListSupplier(
	// 	ConfigurableApplicationContext context) {
	// 	return ServiceInstanceListSupplier.builder()
	// 		.withDiscoveryClient()
	// 		.withWeighted(instance -> ThreadLocalRandom.current().nextInt(1, 101)) // 가중치
	// 		.build(context);
	// }

	// @Bean
	// public ServiceInstanceListSupplier weightedServiceInstanceListSupplier(
	// 	ConfigurableApplicationContext context) {
	// 	return ServiceInstanceListSupplier.builder()
	// 		.withDiscoveryClient()
	// 		.withWeighted(instance -> {
	// 			// 커스텀 가중치 로직
	// 			String weight = instance.getMetadata().get("weight");
	// 			return weight != null ? Integer.parseInt(weight) : 1;
	// 		})
	// 		.build(context);
	// }
}