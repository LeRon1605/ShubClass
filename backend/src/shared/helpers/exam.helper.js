import { randomUUID } from 'crypto';
import { BadRequestException } from '../exceptions/index.js';

class ExamHelper {
    parseQuestionFromFile(text, examId) {
        const regrex = /(?<=\[)[^\][]*(?=])/g;

        const lines = text.trim().split(/\r?\n/).map(x => x.trim())
        const result = [];

        if (!lines || lines.length == 0) {
            throw new BadRequestException('Invalid exam format.');
        }

        try {
            for (let line of lines) {
                let [detail, ...answers] = line.match(regrex);
                let [question, trueAnswer] = detail.split(':').map(x => x.trim());
    
                if (Number(trueAnswer) >= answers.length || Number(trueAnswer) < 0) {
                    throw new BadRequestException('Invalid exam format.');
                }
    
                result.push({
                    id: randomUUID(),
                    question,
                    answers: answers.join('|'),
                    trueAnswer: String.fromCharCode(Number(trueAnswer) + 'A'.charCodeAt(0)),
                    examId,
                })
            }
        } catch {
            throw new BadRequestException('Invalid exam format.');
        }
        
        return result;
    }
}

export default new ExamHelper();