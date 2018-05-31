package model

type DialogflowRequest struct {
	ResponseId string `json:"responseId"`
	QueryResult struct {
		QueryText string `json:"queryText"`
		Parameters struct {
			DateTime struct {
				StartDateTime string `json:"startDateTime"`
				EndDateTime string `json:"endDateTime"`
				DateTime string `json:"date_time"`
			} `json:"date-time"`
			DustType string `json:"dust-type"`
		} `json:"parameters"`
		AllRequiredParamsPresent bool `json:"allRequiredParamsPresent"`
		FulfillmentText string `json:"fulfillmentText"`
		FulfillmentMessages []fulfillmentMessage `json:"fulfillmentMessages"`
		Intent struct {
			Name string `json:"name"`
			DisplayName string `json:"displayName"`
		} `json:"intent"`
		IntentDetectionConfidence float32 `json:"intentDetectionConfidence"`
		DiagnosticInfo struct {
		} `json:"diagnosticInfo"`
		LanguageCode string `json:"languageCode"`
	} `json:"queryResult"`
	OriginalDetectIntentRequest struct {
		Payload struct {
		} `json:"payload"`
	} `json:"originalDetectIntentRequest"`
	Session string `json:"session"`
}

type fulfillmentMessage struct {
	Text struct {
		Text []string `json:"text"`
	} `json:"text"`
}
