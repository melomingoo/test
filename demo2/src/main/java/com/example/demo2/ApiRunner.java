package com.example.demo2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiRunner implements ApplicationRunner {
	@Autowired
	private WebClient.Builder builder;

	@Override
	public void run(ApplicationArguments args ) throws Exception{
		WebClient webClient = builder.build();
		ArrayList<Prodo> neoflux = new ArrayList<Prodo>();

		Prodo f1 = new Prodo("f1","f11");
		Prodo f2 = new Prodo("f2","f22");
		neoflux.add(f1);
		neoflux.add(f2);

		Prodo neo = new Prodo("aa","bb");
		int i=0;
		while(true){

			Mono<Prodo> neoMono = webClient.post().uri("http://localhost:1234/post/mono").bodyValue(neo).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Prodo.class);
			neoMono.subscribe(s->{
				System.out.println(s.getContent());
				System.out.println(s.getTitle());

			});
			Mono<List<Prodo>> neoFlux = webClient.post().uri("http://localhost:1234/post/flux").bodyValue(neoflux).accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Prodo.class).collectList();
			neoFlux.subscribe(s->{
				ArrayList<Prodo> aa = new ArrayList<Prodo>();
				aa.addAll(s);
				for(int t=0;t< aa.size();t++){
					System.out.println(aa.get(t).getContent());
				}
			});

			Thread.sleep(2000);
			if(i==5){
				break;
			}
			i++;
		}

	}

}


