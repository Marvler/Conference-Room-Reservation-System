import { Reservation } from "./Reservation";

export interface ConferenceRoom {
    conferenceRoomId: number;
    conferenceRoomName: string;
    conferenceRoomIdentifier: string;
    level: number;
    availability: boolean;
    numberOfSeats: number;
    numberOfStandings: number;
    organizationId: number;
    reservations: Reservation[];

}