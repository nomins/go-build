package main

import (
	"fmt"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
	"encoding/json"
	"go-build/chatbot/fulfillments/finedust/model"
	"log"
	"net/http"
	"io/ioutil"
	"net/url"
)


func Handler(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	fmt.Println("Received event: ", request.Body)

	var dialogflowRequest model.DialogflowRequest

	if err := json.Unmarshal([]byte(request.Body), &dialogflowRequest); err != nil {
		return events.APIGatewayProxyResponse{}, err
	}

	//fineDustApiUrl := "https://duckju.com/api/v2/findusts"
	dustType := dialogflowRequest.Parameters.DustType
	province := dialogflowRequest.Parameters.Province
	//findDustInfo, err := getFineDustInfo(fineDustApiUrl, dustType, province)
	//if err != nil {
	//	log.Fatalln(err)
	//}
	text := fmt.Sprintf("%s의 %s가 궁금하신거군요! 금방 알려줄게요!", province, dustType)

	body := &model.DialogflowResponse {
		FulfillmentText: text,
		Payload: model.Payload{
			Slack: model.Slack{
				Text: text,
			},
		},
	}

	bodyJson, err := json.Marshal(body)
	if err != nil {
		return events.APIGatewayProxyResponse{}, err
	}

	return events.APIGatewayProxyResponse{
		Headers: map[string]string {
			"Content-Type": "application/json;charset=utf-8",
		},
		Body: string(bodyJson),
		StatusCode: 200,
	}, nil
}

func getFineDustInfo(apiUrl, dustType, province string) (model.FineDustInfo, error) {
	queryValues := url.Values{}
	queryValues.Set("type", dustType)
	queryValues.Set("province", province)

	request := fmt.Sprintf("%s?%s", apiUrl, queryValues.Encode())

	response, err := http.Get(request)
	if err != nil {
		log.Fatal(err)
	}

	if response.StatusCode != 200 {
		log.Fatal(fmt.Sprintf("미세먼지 API로부터 정상응답을 받지 못했습니다. (상태코드:%d)", response.StatusCode))
	}

	defer response.Body.Close()
	body, err := ioutil.ReadAll(response.Body)
	if err != nil {
		log.Fatalln(err)
	}

	var fineDustInfo model.FineDustInfo
	json.Unmarshal(body, &fineDustInfo)

	return fineDustInfo, nil
}


func main() {
	lambda.Start(Handler)
}

