package org.voduybao.bookstorebackend.services.shared.Impl;

import co.elastic.clients.elasticsearch._types.KnnSearch;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.search.FieldCollapse;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import co.elastic.clients.json.JsonData;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.query.BaseQuery;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class NativeQuery extends BaseQuery {
    @Nullable
    private final Query query;
    @Nullable
    private org.springframework.data.elasticsearch.core.query.Query springDataQuery;
    @Nullable
    private Query filter;
    private final Map<String, Aggregation> aggregations = new LinkedHashMap();
    @Nullable
    private Suggester suggester;
    @Nullable
    private FieldCollapse fieldCollapse;
    private List<SortOptions> sortOptions = Collections.emptyList();
    private Map<String, JsonData> searchExtensions = Collections.emptyMap();
    @Nullable
    private List<KnnSearch> knnSearches = Collections.emptyList();

    public NativeQuery(NativeQueryBuilder builder) {
        super(builder);
        this.query = builder.getQuery();
        this.filter = builder.getFilter();
        this.aggregations.putAll(builder.getAggregations());
        this.suggester = builder.getSuggester();
        this.fieldCollapse = builder.getFieldCollapse();
        this.sortOptions = builder.getSortOptions();
        this.searchExtensions = builder.getSearchExtensions();
        if (builder.getSpringDataQuery() != null) {
            Assert.isTrue(!NativeQuery.class.isAssignableFrom(builder.getSpringDataQuery().getClass()), "Cannot add an NativeQuery in a NativeQuery");
        }

        this.springDataQuery = builder.getSpringDataQuery();
        this.knnSearches = builder.getKnnSearches();
    }

    public NativeQuery(@Nullable Query query) {
        this.query = query;
    }

    public static NativeQueryBuilder builder() {
        return new NativeQueryBuilder();
    }

    @Nullable
    public Query getQuery() {
        return this.query;
    }

    @Nullable
    public Query getFilter() {
        return this.filter;
    }

    public Map<String, Aggregation> getAggregations() {
        return this.aggregations;
    }

    @Nullable
    public Suggester getSuggester() {
        return this.suggester;
    }

    @Nullable
    public FieldCollapse getFieldCollapse() {
        return this.fieldCollapse;
    }

    public List<SortOptions> getSortOptions() {
        return this.sortOptions;
    }

    public Map<String, JsonData> getSearchExtensions() {
        return this.searchExtensions;
    }

    public void setSpringDataQuery(@Nullable org.springframework.data.elasticsearch.core.query.Query springDataQuery) {
        this.springDataQuery = springDataQuery;
    }

    @Nullable
    public List<KnnSearch> getKnnSearches() {
        return this.knnSearches;
    }

    @Nullable
    public org.springframework.data.elasticsearch.core.query.Query getSpringDataQuery() {
        return this.springDataQuery;
    }
}

