export const encodeFileToBase64 = image => {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(image);
    reader.onload = e => {
      return resolve(e.target.result);
    };
    reader.onerror = e => reject(e);
  });
};
