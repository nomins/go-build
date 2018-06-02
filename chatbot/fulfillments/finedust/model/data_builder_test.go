package model

import "testing"

func TestBuildFulfillmentRequest(t *testing.T) {

	parameters := QueryParameter{
		Name: "test parameter",
	}

	fRequest := BuildAPIGatewayProxyRequest("test input", parameters)

	t.Log(fRequest.Body)
}
