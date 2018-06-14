package model

import (
	"testing"
	"encoding/json"
	"log"
)

func TestBuildFulfillmentRequest(t *testing.T) {

	queryText := "서울 미세먼지 어때요?"
	parameters := QueryParameter{
		Province: "서울",
		DustType: "미세먼지",
	}

	apiGwRequest := BuildAPIGatewayProxyRequest(queryText, parameters)

	var body DialogflowRequest
	err := json.Unmarshal([]byte(apiGwRequest.Body), &body)
	if err != nil {
		log.Fatalln(err)
	}

	if body.QueryText != queryText {
		t.Errorf("QueryText는 같아야 합니다. (actual: %s, expected: %s)",
			body.QueryText, queryText)
	}
	if body.Parameters.DustType != parameters.DustType {
		t.Errorf("DustType는 같아야 합니다. (actual: %s, expected: %s)",
			body.Parameters.DustType, parameters.DustType)
	}
	if body.Parameters.Province != parameters.Province {
		t.Errorf("Province는 같아야 합니다. (actual: %s, expected: %s)",
			body.Parameters.Province, parameters.Province)
	}

}