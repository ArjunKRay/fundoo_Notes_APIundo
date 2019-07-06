package com.bridgelabz.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.notes.model.Note;
import com.bridgelabz.utility.ITockenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchImpl implements ElasticSearchService {

	
	
	
	private final String INDEX= "fundooNote";
	private final String TYPE = "bridgeLabz";
	
	@Autowired
	ITockenGenerator tockenGenerator;
	@Autowired
	RestHighLevelClient client;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	@Override
	public String createNote(Note note) {
    
	  @SuppressWarnings({"unchecked"})
	  Map<String,Object> mapDocument= objectMapper.convertValue(note,Map.class);
	  
	  @SuppressWarnings("deprecation")
      IndexRequest indexRequest = new IndexRequest(INDEX,TYPE, note.getUserId()).source(mapDocument);
	  IndexResponse IndexResponse = null;
      try {
		IndexResponse  = client.index(indexRequest, RequestOptions.DEFAULT);
	} catch (IOException e) {
		e.printStackTrace();
	}
      return IndexResponse.getResult().name();
	
	}

	@Override
	public String deleteNote(String noteId) {
		@SuppressWarnings("deprecation")
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, noteId);
		DeleteResponse response=null;
		try {
			response = client.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.getResult().name();
	}
	

	@Override
	public String updateNote(Note note) {
		@SuppressWarnings({"uncheched"})
		Map<String,Object> mapDocument = objectMapper.convertValue(note,Map.class);
		@SuppressWarnings("deprecation")
		UpdateRequest updateRequest = new UpdateRequest(INDEX,TYPE,note.getUserId()).doc(mapDocument);
		UpdateResponse updateResponse = null;
		try{
			updateResponse =client.update(updateRequest, RequestOptions.DEFAULT);
		  }catch(IOException e) {
			  
			  e.printStackTrace();
		  }
		return updateResponse.getResult().name();
				
	}

	@Override
	public Note findByNoteId(String noteId) {
		@SuppressWarnings("deprecation")
		GetRequest getRequest = new GetRequest(INDEX, TYPE,noteId);
		GetResponse getResponse = null;
		try {
			getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		    } catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> resultMap = getResponse.getSource();

		return objectMapper.convertValue(resultMap, Note.class);
	}

	@Override
	public List<Note> elasticSearch(String getString, String tocken) {
	    String userId = tockenGenerator.verifyTocken(tocken);
		QueryBuilder queryBuilder= QueryBuilders.boolQuery()
				                       .must(QueryBuilders.queryStringQuery("*"+getString+"*")
				                       .analyzeWildcard(true).field("title").field("description"))
				                       .filter(QueryBuilders.termQuery("userId", userId));
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		                   sourceBuilder.query(queryBuilder);
		  SearchRequest searchRequest = new SearchRequest();
		  searchRequest.source(sourceBuilder);
		 SearchResponse searchResponse =null;
		 
		 try {
			searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		return  getSearchResult(searchResponse);
		 
		 
	}

	private List<Note> getSearchResult(SearchResponse searchResponse) {
		SearchHit[] searchHit = searchResponse.getHits().getHits();

		List<Note> profileDocuments = new ArrayList<>();

		if (searchHit.length > 0) {

			Arrays.stream(searchHit)
					.forEach(hit -> profileDocuments.add(objectMapper.convertValue(hit.getSourceAsMap(), Note.class)));
		}

		return profileDocuments;
	
	}

}
