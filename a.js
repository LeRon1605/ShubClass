const re = /(?<=\[)[^\][]*(?=])/g;

const txt = `
    [Màu gì đây:0] [Vàng] [Đỏ] [Tím]
    [Con gì đây:1] [Chó] [Mèo] [Gà]
`.trim().split(/\r?\n/).map(x => x.trim());

for (let line of txt) {
    let [questionDetail, ...answers] = line.match(re);
    questionDetail = questionDetail.split(':').map(x => x.trim());

    if (Number(questionDetail[1]) >= answers.length || Number(questionDetail[1]) < 0) {
        console.log('fail');
    }

    console.log( {
        question: questionDetail[0],
        trueAnswer: String.fromCharCode(Number(questionDetail[1]) + 'A'.charCodeAt(0)),
        answers
    });
}