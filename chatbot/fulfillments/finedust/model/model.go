package model

type QueryParameter struct {
	Province string `json:"province"`
	DustType string `json:"dust-type"`
}

type QueryResult struct {
	QueryText string `json:"queryText"`
	Parameters QueryParameter `json:"parameters"`
	AllRequiredParamsPresent bool `json:"allRequiredParamsPresent"`
	FulfillmentText string `json:"fulfillmentText"`
	FulfillmentMessages []FulfillmentMessage `json:"fulfillmentMessages"`
	Intent struct {
		Name string `json:"name"`
		DisplayName string `json:"displayName"`
	} `json:"intent"`
	IntentDetectionConfidence float32 `json:"intentDetectionConfidence"`
	DiagnosticInfo struct {
	} `json:"diagnosticInfo"`
	LanguageCode string `json:"languageCode"`
}

type DialogflowRequest struct {
	ResponseId string `json:"responseId"`
	QueryResult `json:"queryResult"`
	OriginalDetectIntentRequest struct {
		Payload struct {
		} `json:"payload"`
	} `json:"originalDetectIntentRequest"`
	Session string `json:"session"`
}

type FulfillmentMessage struct {
	Text struct {
		Text []string `json:"text"`
	} `json:"text"`
	Card struct {
		Title string `json:"title"`
		Subtitle string `json:"subtitle"`
		ImageUri string `json:"imageUri"`
		Buttons []Button `json:"buttons"`
	}
}

type Button struct {
	Text string `json:"text"`
	Postback string `json:"postback"`
}

type Payload struct {
	Google struct {
		ExpectUserResponse bool `json:"expectUserResponse"`
		RichResponse struct {
			Items []Item `json:"items"`
		} `json:"richResponse"`
	} `json:"google"`
	Facebook struct {
		Text string `json:"text"`
	} `json:"facebook"`
	Slack `json:"slack"`
}

type Slack struct {
	Text string `json:"text"`
}

type DialogflowResponse struct {
	FulfillmentText string `json:"fulfillmentText"`
	FulfillmentMessages []FulfillmentMessage `json:"fulfillmentMessages"`
	Source string `json:"source"`
	Payload `json:"payload"`
	OutputContexts []OutputContext `json:"outputContexts"`
	FollowupEventInput struct {
		Name string `json:"name"`
		LanguageCode string `json:"languageCode"`
		Parameters struct {
			Param string `json:"param"`
		} `json:"parameters"`
	} `json:"followupEventInput"`
}

type Item struct {
	SimpleResponse struct {
		TextToSpeech string `json:"textToSpeech"`
	} `json:"simpleResponse"`
}

type OutputContext struct {
	Name string `json:"name"`
	LifespanCount int `json:"lifespanCount"`
	Parameters struct {
		Param string `json:"param"`
	} `json:"parameters"`
}

type FineDustInfo struct {
	FineDusts []*struct {
		DateTime string `json:"dateTime"`
		Province string `json:"province"`
		Figure string `json:"figure"`
		Status string `json:"status"`
	}
}
