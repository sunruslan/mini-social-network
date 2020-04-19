import React, {useState} from "react";
import style from './Paginator.module.css';

let Paginator = ({totalItemsCount, pageSize, currentPage, onPageChanged, portionSize = 10}) => {
    let pagesCount = Math.ceil(totalItemsCount / pageSize);

    let pages = [];
    for (let i = 1; i <= pagesCount; i++) {
        pages.push(i);
    }

    let portionsCount = Math.ceil(pagesCount / portionSize);
    let [portionNumber, setPortionNumber] = useState(Math.ceil(currentPage / portionSize));
    let leftPortionPageNumber = (portionNumber - 1) * portionSize + 1;
    let rightPortionPageNumber = portionNumber * portionSize;

    return (
        <ul className={'pagination'}>
            {portionNumber > 1 &&
            <li className={'page-item'}><button className={'page-link'}
                                                onClick={() => setPortionNumber(portionNumber - 1)}>PREV</button></li>}

            {pages.filter(p => p >= leftPortionPageNumber && p <= rightPortionPageNumber)
                .map(p => <li className={'page-item'}><span className={'page-link ' + (p === currentPage ? style.isActive: '')}
                                    key={p} onClick={(e) => onPageChanged(p)}>{p}</span></li>)}

            {portionNumber < portionsCount &&
            <li className={'page-item'}><button className={'page-link'}
                                                onClick={() => setPortionNumber(portionNumber + 1)}>NEXT</button></li>}
        </ul>
    );
};

export default Paginator;