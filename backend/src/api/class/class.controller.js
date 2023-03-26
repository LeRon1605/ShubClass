import { ForbiddenException } from "../../exceptions/index.js";
import ClassService from "./class.service.js";
import { ClassCreateDto, ClassUpdateDto } from "./dtos/index.js";
class ClassController {
  async getAllClasses(req, res, next) {
    try {
      let data = null;
      if (req.session.role == "Student") {
        data = await ClassService.getAllClassesOfStudent(req.session.id);
      } else {
        data = await ClassService.getAllClassesOfTeacher(req.session.id);
      }
      return res.status(200).json(data);
    } catch (error) {
      next(error);
    }
  }

  async createClass(req, res, next) {
    try {
      if (req.session.role == "Teacher") {
        const entity = ClassCreateDto.toEntity({
          ...req.body,
          teacherId: req.session.id,
        });
        const result = await ClassService.createClass(entity);
        return res.status(201).json(result);
      } else {
        throw new ForbiddenException("You are not allowed to create class.");
      }
    } catch (error) {
      next(error);
    }
  }

  async updateClass(req, res, next) {
    try {
      const entity = ClassUpdateDto.toEntity({
        ...req.body,
        teacherId: req.session.id,
      });
      const result = await ClassService.updateClass(req.params.id, entity);
      return res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  }

  async deleteClass(req, res, next) {
    try {
      await ClassService.deleteClass(req.params.id, req.session.id);
      return res.status(200).json();
    } catch (error) {
      next(error);
    }
  }
}

export default new ClassController();
