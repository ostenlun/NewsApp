package com.codermages.NewsApp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codermages.NewsApp.domain.Article;
import com.codermages.NewsApp.gateways.StorageGateway;

@Service
public class StorageService {
    private final StorageGateway storageGateway;

    //@Autowired
    public StorageService(@Value("${MOCKSTORAGE_URL:placeholder}") String urlStorage) {
        this.storageGateway = new StorageGateway(new RestTemplate(), urlStorage);
    }

    public ResponseEntity<?> storeNewsArticle(Article article) {
        return storageGateway.storeData(article.getTitle());
    }

    public ResponseEntity<?> storeData(String data) {
        return storageGateway.storeData(data);
    }

    public ResponseEntity<?> getData(String userId) {
        return storageGateway.getData(userId);
    }
}
