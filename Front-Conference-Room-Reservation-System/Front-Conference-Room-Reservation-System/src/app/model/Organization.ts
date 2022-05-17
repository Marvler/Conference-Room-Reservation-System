import { ConferenceRoom } from "./ConferenceRoom";

export interface Organization {
    organizationName: string;
    email: string;
    password: string;
    conferenceRoomsList: ConferenceRoom[];

}