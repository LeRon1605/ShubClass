import sequelize from '../../database/models/index.cjs';
import { ClassDto } from './dtos/index.js';
import { REQUEST_STATE } from '../../shared/enum/index.js';
import { EntityNotFoundException, EntityAlreadyExistException, EntityForbiddenUpdateException, EntityForbiddenDeleteException } from '../../exceptions/index.js';

const { User, Class, StudentClass } = sequelize;
class ClassService {
    async getAllClassesOfTeacher(userId) {
        const user = await User.findByPk(userId);
        const data = await Class.findAll({
            where: {
                teacherId: userId
            }
        });
        return data.map(x => ClassDto.toDto(x));
    }

    async getAllClassesOfStudent(userId) {
        const user = await User.findByPk(userId);
        if (user == null) {
            throw new EntityNotFoundException('User', userId);
        }
        const data = await StudentClass.findAll({
            where: {
                studentId: userId,
                state: REQUEST_STATE.APPROVED
            },
            include: [{
                model: Class
            }]
        });
        return data.map(x => ClassDto.toDto(x.Class));
    }

    async createClass(newClass) {
        const classEntity = await Class.findByPk(newClass.id);
        if (classEntity != null) {
            throw new EntityAlreadyExistException('Class', newClass.id);
        }

        const teacher = await User.findByPk(newClass.teacherId);
        if (teacher == null) {
            throw new EntityNotFoundException('User', newClass.teacherId);
        }

        return await Class.create(newClass);
    }

    async updateClass(id, newClass) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        const teacher = await User.findByPk(newClass.teacherId);
        if (teacher == null) {
            throw new EntityNotFoundException('User', newClass.teacherId);
        }

        if (teacher.id != classEntity.teacherId) {
            throw new EntityForbiddenUpdateException('Class', classEntity.id);
        }

        await Class.update({ ...classEntity, ...newClass }, {
            where: {
                id: id
            }
        });

        const dto = ClassDto.toDto(await Class.findByPk(id));
        return dto;
    }

    async deleteClass(id, teacherId) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }
        
        if (classEntity.teacherId != teacherId) {
            throw new EntityForbiddenDeleteException('Class', id);
        }

        await Class.destroy({
            where: {
                id: id
            }
        });
    }
}

export default new ClassService();