/*
package com.sparta.msa_exam.gateway;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ServerPortGlobalFilter implements GlobalFilter {

	private final DiscoveryClient discoveryClient;

	public ServerPortGlobalFilter(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("server port filter");
		log.info("ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR : " + ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
		log.info("exchange.getAttribute : " + exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR));

		// 현재 요청 라우트의 대상 서비스 ID 가져오기
		String serviceId = "";

		Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

		if (route != null) {
			serviceId = route.getId();
		}
		log.info("serviceId: " + serviceId);

		if (!serviceId.isEmpty()) {
			// 서비스 ID로 Eureka에서 인스턴스 포트 조회
			List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

			if (!instances.isEmpty()) {
				String port = String.valueOf(instances.get(0).getPort());
				exchange.getResponse().getHeaders().add("Server-Port", port);
			}
		}
		return chain.filter(exchange);
	}

}
*/
