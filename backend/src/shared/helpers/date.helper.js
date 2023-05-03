class DateHelper {
    getCurrentDate() {
        return new Date(Date.now() + 7 * 3600 * 1000);
    }
}

export default new DateHelper();
