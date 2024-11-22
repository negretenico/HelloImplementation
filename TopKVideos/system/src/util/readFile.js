import { promises as fs } from "fs"; 

export const readFile = async (filePath) => {
  try {
    const fileContent = await fs.readFile(filePath, "utf-8");
    return JSON.parse(fileContent); // Parse the JSON content
  } catch (error) {
    console.error("Error reading the file:", error.message);
    throw error;
  }
};
