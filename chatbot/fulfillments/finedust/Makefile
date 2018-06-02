test:
    go test go-build/chatbot/fulfillments/finedust/model -v

build:
	dep ensure
	env GOOS=linux go build -ldflags="-s -w" -o bin/main main/main.go
