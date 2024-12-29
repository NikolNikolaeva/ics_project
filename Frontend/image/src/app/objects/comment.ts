import {Image} from "./image";

export interface Comment{
  id:number;
  comment:String;
  author:string;
  date:Date;
  image: Image;
}
