package com.example.OnlinesalesTask2;

import com.example.OnlinesalesTask2.Services.MathjsApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class OnlinesalesTask2ApplicationTests {

		MathjsApiService requestService;
		RestTemplateBuilder restTemplateBuilder;
		@Value("${webapi.url}")
		String webApiUrl;
	OnlinesalesTask2ApplicationTests(@Value("${webapi.url}") String webApiUrl){
			this.restTemplateBuilder=new RestTemplateBuilder();
			this.requestService=new MathjsApiService(restTemplateBuilder, webApiUrl);
		}
		@DisplayName("Junit test for testing the getExpressionsResult method of RequestService")
		@Test
		public void test_getExpressionsResult() throws URISyntaxException, ExecutionException, InterruptedException {
			int testDataSize=0,i;
			String encodedExpression;
			String[] testData=new String[]{"(5*4)/0", "sqrt(3*3+4*4)", "25^2", "21*12*53*44","1231+5883+482*3+21^4"};
			String[] results=new String[]{"Infinity", "5", "625", "5.87664e+5", "2.03041e+5"};
			testDataSize=testData.length;
			String[] encodedTestData=new String[testDataSize];
			ExecutorService executorService= Executors.newFixedThreadPool(10);
			for(i=0;i<testDataSize;i++) {
				encodedExpression = URLEncoder.encode(testData[i], StandardCharsets.UTF_8);
				encodedTestData[i]=encodedExpression;
				//encodedToData.put(encodedExpression, data[i]);
			}
			List<Future<ResponseEntity<String>>> responses =new ArrayList<>();
			for(i=0;i<testDataSize;i++){
				Future<ResponseEntity<String>> response= requestService.getExpressionResult(encodedTestData[i],executorService,null);
				responses.add(response);
			}
			for(i=0;i<testDataSize;i++){
				assertThat(responses.get(i).get().getBody(), is(results[i]));
			}

		}


	}


