import { readFile } from "../src/readFile.js";
export const fetchMockedData = async () => {
  return await readFile("./data/MOCK_DATA.json");
};
