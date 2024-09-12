import styled from "styled-components";

export const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
    font-size: 16px;
`;

export const TableHeader = styled.thead`
    background-color: #333;
    color: white;

    
`;

export const TableBody = styled.tbody``;

export const TableRow = styled.tr`
    border-bottom: 1px solid #DDD;
`;

export const TableData = styled.td`
    padding: 12px;
    text-align: left;
    border: 1px solid #DDD;
`;

export const Icon = styled.span`
    margin: 0 8px;
    cursor: pointer;
    color: black;

    &:hover {
        color: orange;
    }
`;