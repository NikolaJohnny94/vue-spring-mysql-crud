export const checkIfMailExists = (users, email) => {
  const duplicate = users.filter((user) => user.email === email)

  if (duplicate.length > 0) {
    return true
  }
}
