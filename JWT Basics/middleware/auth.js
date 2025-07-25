const jwt = require("jsonwebtoken");
const {UnautenticatedError} = require("../errors");
const authenticationMiddleware = async (req, res, next) => {
  const authHeader = req.headers.authorization;
  if (!authHeader || !authHeader.startsWith("Bearer ")) {
    throw new UnautenticatedError("No Token provided");
  }
  const token = authHeader.split(" ")[1];
  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    const {id,username} = decoded;
    req.user = { username, id};
  } catch (error) {
    throw new UnautenticatedError("Not authorized to access this route");
  }
  next();
};

module.exports = authenticationMiddleware;
