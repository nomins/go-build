package main

import (
	"testing"
	"go-build/chatbot/fulfillments/finedust/model"
	"encoding/json"
)

func TestSum (t *testing.T) {
	queryText := "서울 미세먼지 어때요?"
	parameters := model.QueryParameter{
		Province: "서울",
		DustType: "미세먼지",
	}

	apiGwRequest := model.BuildAPIGatewayProxyRequest(queryText, parameters)

	apiGwResponse, err := Handler(apiGwRequest)
	if err != nil {
		t.Error(err)
	}

	if apiGwResponse.StatusCode != 200 {
		t.Errorf("StatusCode는 200이어야 합니다. (actual: %d, expected: %d)",
			apiGwResponse.StatusCode, 200)
	}

	var body model.DialogflowResponse

	err = json.Unmarshal([]byte(apiGwResponse.Body), &body)
	if err != nil {
		t.Error(err)
	}
}
