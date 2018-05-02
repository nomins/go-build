// link: https://rominirani.com/hands-on-with-api-ai-google-assistant-writing-your-first-conversation-agent-a6a7dcdaba27

const express = require('express');
const bodyParser = require('body-parser');
const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/version', (req, res) => {
    res.status(200).send("APIAI Webhook Integration. Version 1.0");
});

app.get('/', (req, res) => {
    res.status(200).send("Hello from APIAI Webhook Integration.");
});

/* Handling all messenges */
app.post('/webhook', (req, res) => {
    console.log(req.body);
    console.log(req.body.queryResult.parameters["rating"]);
    console.log(req.body.queryResult.parameters["comments"]);
    console.log(req.body.queryResult.parameters["resort-location"]);
    //Persist this in some database
    //Send out an email that new feedback has come in
    res.status(200).json({
        fulfillmentText: 'Seunghyun!! Thank you for the feedback~!',
    });
});

const server = app.listen(process.env.PORT || 5000, () => {
  console.log('Express server listening on port %d in %s mode', server.address().port, app.settings.env);
});