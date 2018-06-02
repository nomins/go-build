package main

import (
	"testing"
	"github.com/aws/aws-lambda-go/events"
)

func TestSum (t *testing.T) {
	testRequest := events.APIGatewayProxyRequest{
		Body: "This is a test body.",
	}
	//
	response, err := Handler(testRequest)
	if err != nil {
		t.Error(err)
	}

	t.Log(response.Body)

}
