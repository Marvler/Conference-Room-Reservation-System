import { ConferenceRoom } from "./ConferenceRoom";

export interface Organization {
    organizationId: number;
    organizationName: string;
    email: string;
    password: string;
    conferenceRooms: ConferenceRoom[];

}