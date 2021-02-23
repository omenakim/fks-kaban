import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';


export interface MessageData {
  messages: String[];
  type: string;
}

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  messageData: MessageData = {
    messages: [],
    type: 'success'
  }

  constructor() { }

  showMessage(messages: string[], type: string) {
    this.messageData = {
      messages: messages,
      type: type
    }
    setTimeout(() => {
      this.messageData = {
        messages: [],
        type: 'success'
      }
    }, 10000);
  }

}
