package model

import (
	"encoding/json"
	"log"
	"github.com/aws/aws-lambda-go/events"
)

func BuildAPIGatewayProxyRequest(input string, parameters QueryParameter) events.APIGatewayProxyRequest {
	requestJson := buildFulfillmentRequest(input, parameters)

	return events.APIGatewayProxyRequest{
		Body: string(requestJson),
	}
}

func buildFulfillmentRequest(queryText string, parameters QueryParameter) []byte {
	request := DialogflowRequest{
		ResponseId: "fakeResponseId",
		QueryResult: QueryResult{
			QueryText:  queryText,
			Parameters: parameters,
		},
	}
	
	requestJson, err := json.Marshal(&request)
	if err != nil {
		log.Fatalln(err)
	}

	return requestJson
}
