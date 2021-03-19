package ru.dsoccer.graphql.exception;

import graphql.GraphQLException;

public class GraphqlEntityNotFound extends GraphQLException {

  public GraphqlEntityNotFound(String message) {
    super(message);
  }
}
