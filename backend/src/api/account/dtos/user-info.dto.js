import { DEFAULT_AVATAR } from '../../../shared/enum/index.js';
class Dto {
    toEntity(dto) {
        return {};
    }

    toDto(entity) {
        return {
            id: entity.id,
            email: entity.email,
            name: entity.User.name,
            phoneNumber: entity.User.phoneNumber,
            dateOfBirth: entity.User.dateOfBirth,
            school: entity.User.school,
            grade: entity.User.grade,
            address: entity.User.address,
            gender: entity.User.gender,
            avatar: entity.User.gender ? DEFAULT_AVATAR.MAN : DEFAULT_AVATAR.WOMAN
        };
    }
}

const UserInfoDto = new Dto();

export { UserInfoDto };
