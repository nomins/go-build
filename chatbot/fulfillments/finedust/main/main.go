package main

import (
	"fmt"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
	"encoding/json"
	"go-build/chatbot/fulfillments/finedust/model"
)


func Handler(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	fmt.Println("Received body: ", request.Body)

	var dialogflowRequest model.DialogflowRequest

	if err := json.Unmarshal([]byte(request.Body), &dialogflowRequest); err != nil {
		return events.APIGatewayProxyResponse{}, err
	}

	fmt.Printf("Parsed JSON Body: %+v", dialogflowRequest)

	return events.APIGatewayProxyResponse{
		Body: request.Body,
		StatusCode: 200,
	}, nil
}


func main() {
	lambda.Start(Handler)
}

