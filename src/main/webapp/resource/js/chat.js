var wsUri;
var websocket;
var nickName;
var message;
var chatWindow;

$(document).ready(function() {
    nickName = $('#nickname');
    message = $('#message');
    chatWindow = $('#response');
    $('.chat-wrapper').hide();
    nickName.focus();
    wsUri = "ws://localhost:8080/javaee_chat_war/websocket";
    setUpWebSocket();

    $('#enterRoom').click(function(evt) {
        evt.preventDefault();
        $('.chat-wrapper h2').text('User: @'+nickName.val());
        $('.chat-signin').hide();
        $('.chat-wrapper').show();
        message.focus();
        join();
    });
    $('#do-chat').submit(function(evt) {
        evt.preventDefault();
        sendMessage();
    });

    $('#leave-room').click(function(){
        $('.chat-wrapper').hide();
        $('.chat-signin').show();
    });
});
function join() {
    var msg = '{"message":"joined to the chat", "sender":"'
        + nickName.val() + '"}';
    console.log(msg);
    websocket.send(msg);
    console.log("USER JOINED");
}

function sendMessage() {
    var msg = '{"message":"' + message.val() + '", "sender":"'
        + nickName.val() + '"}';
    websocket.send(msg);
    message.val('').focus();
}

function onOpen() {
    console.log("CONNECTED");
}

function onClose() {
    console.log("DISCONNECTED");
}

function onMessage(evt) {

    var msg = JSON.parse(evt.data);
    var $messageLine = $('<tr><td class="user label label-info">' + msg.sender
        + '</td><td class="message badge">' + msg.message
        + '</td></tr>');
    chatWindow.append($messageLine);
}

function onError(evt) {
    console.log("ERROR");
}

function disconnect() {
    websocket.close();
}

function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    $("#output").appendChild(pre);
}

function setUpWebSocket(){
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) { onOpen(evt); };
    websocket.onmessage = function(evt) { onMessage(evt); };
    websocket.onerror = function(evt) { onError(evt); };
    websocket.onclose = function(evt) { onClose(evt); };
}