export const reqiuredField = value => {
    if (value) {
        return undefined;
    }
    return "Field is required.";
};

export const checkSize = value => {
    if (value.length < 30) {
        return undefined;
    }
    return "Max size limit.";
};