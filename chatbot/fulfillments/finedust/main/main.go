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

	body := &model.DialogflowResponse {
		FulfillmentText: "Today is fine",
	}

	bodyJson, err := json.Marshal(body)
	if err != nil {
		return events.APIGatewayProxyResponse{}, err
	}

	return events.APIGatewayProxyResponse{
		Body: string(bodyJson),
		StatusCode: 200,
	}, nil

	return events.APIGatewayProxyResponse{
		Body: "coffee~!",
		StatusCode: 200,
	}, nil
	//return events.APIGatewayProxyResponse{}, errors.New("my Error")

}


func main() {
	lambda.Start(Handler)
}

