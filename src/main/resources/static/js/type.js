var a = {
    "title": "String",  //title
    "q": [ // qbar
        {// q local
            "question": String,
            "type":  ENUM,
            "totalMark": Integer,
            "choices": [ //choices local
                {
                    "choice": String,
                    "mark": Integer,
                    "ans": Boolean,
                }
            ]
        }
    ]
}