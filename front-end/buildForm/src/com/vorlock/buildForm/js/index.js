//parsing JSON data from txtarea
function handleButtonClickBuildForm() {
    var text = document.getElementById("txtAreaDataJSON").value;
    try {
        var jsonObject = JSON.parse(text);
        // var jsonObj = JSON.parse(document.getElementById("txtAreaDataJSON").value.replace(/\s+/g, ""));
        buildFormFromJson(jsonObject);
    }
    catch (e) {
        alert('Error ' + e.name + ":" + e.message + "\n" + e.stack);
    }
}

//here we'll drawing our form
function buildFormFromJson(jsonObject) {
    var typeName;
    Object.keys(jsonObject).forEach(function (key) {
        switch (key) {
            case 'type':
                typeName = jsonObject[key];
                var element = document.createElement(typeName);
                break;
            case 'items':
                fillContainer(jsonObject[key], typeName);
                break;
        }
    });
}

function fillContainer(array, typeName) {
    var container = document.createElement(typeName);
    array.forEach(function (item, i) {
        var component = createComponent(item);
        if (typeof component.labelBlock != 'undefined')
            container.appendChild(component.labelBlock);
        container.appendChild(component.element);
    });
    var assignment = document.getElementById("view-container");
    assignment.appendChild(container);
}

function createComponent(jsonObject) {
    switch (jsonObject.type) {
        case 'title':
            return titleComponent(jsonObject);
        case 'input':
            return inputComponent(jsonObject);
        case 'checkbox':
            return checkboxComponent(jsonObject);
    }
}

function inputComponent(jsonObject) {
    var complexElement = {};
    var label = document.createElement('label');
    var labelText = document.createTextNode(jsonObject.label);
    label.appendChild(labelText);
    var labelBlock = document.createElement('div');
    labelBlock.className = "form-row";
    labelBlock.appendChild(label);
    var elementBlock = document.createElement('div');
    var element = document.createElement('input');
    element.type = jsonObject.type;
    if (typeof jsonObject.id != 'undefined')
        element.id = jsonObject.id;
    elementBlock.appendChild(element);
    elementBlock.className = "form-row";
    complexElement['element'] = elementBlock;
    complexElement['labelBlock'] = labelBlock;
    return complexElement;
}

function checkboxComponent(jsonObject) {
    var complexElement = {};
    var label = document.createElement('label');
    var labelText = document.createTextNode(jsonObject.label);
    label.appendChild(labelText);
    var element = document.createElement('input');
    element.type = jsonObject.type;
    if (typeof jsonObject.id != 'undefined')
        element.id = jsonObject.id;
    var elementBlock = document.createElement('div');
    elementBlock.appendChild(element);
    elementBlock.appendChild(label);
    elementBlock.className = "checkbox-row";
    complexElement['element'] = elementBlock;
    return complexElement;
}

function titleComponent(jsonObject) {
    var complexElement = {};
    var element = document.createElement('tittle');
    var labelText = document.createTextNode(jsonObject.label);
    element.appendChild(labelText);
    if (typeof jsonObject.id != 'undefined')
        element.id = jsonObject.id;
    complexElement['element'] = element;
    return complexElement;
}

//putting JSON data into textarea on first load page
var jsonObject = {
    "type": "form",
    "items": [
        {
            "type": "title",
            "label": "Request a callback"
        },
        {
            "type": "input",
            "label": "First Name",
            "id": "firstName"
        },
        {
            "type": "input",
            "label": "Last Name",
            "id": "lastName"
        },
        {
            "type": "input",
            "label": "Email",
            "id": "email"
        },
        {
            "type": "input",
            "label": "When is the best time to call you?",
            "id": "timeToCall"
        },
        {
            "type": "checkbox",
            "label": "consent to my submitted data being collected & stored",
            "id": "agreement"
        }
    ]
};
var text = JSON.stringify(jsonObject);
document.getElementById("txtAreaDataJSON").value = text;


//reset textarea
function handleButtonClickRestoreForm() {
    location.reload();
}

//auto-size for textarea
var textAreaAutoSize = document.getElementsByTagName('textarea');
for (var i = 0; i < textAreaAutoSize.length; i++) {
    textAreaAutoSize[i].setAttribute('style', 'height:' + (textAreaAutoSize[i].scrollHeight) + 'px;overflow-y:hidden;');
    textAreaAutoSize[i].addEventListener("input", OnInput, false);
}

function OnInput() {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
}