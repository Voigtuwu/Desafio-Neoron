import styled from "styled-components";

export const Container = styled.div`
    overflow-x: auto;
`;

export const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
    font-size: 16px;

    /* Responsividade */
    @media (max-width: 768px) {
        font-size: 14px;
    }

    @media (max-width: 480px) {
        font-size: 12px;
    }
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
    max-width: 250px;
    padding: 12px;
    text-align: left;
    border: 1px solid #DDD;

    /* Responsividade */
    @media (max-width: 768px) {
        padding: 10px;
    }

    @media (max-width: 480px) {
        padding: 8px;
        max-width: 200px;
    }
`;

export const Icon = styled.span`
    margin: 0 8px;
    cursor: pointer;
    color: black;

    &:hover {
        color: orange;
    }

    /* Responsividade */
    @media (max-width: 768px) {
        margin: 0 6px;
    }

    @media (max-width: 480px) {
        margin: 0 4px;
    }
`;
